const puppeteer = require('puppeteer'); // v13.0.0 or later

(async () => {
  const browser = await puppeteer.launch({ headless: false });
  const page = await browser.newPage();
  const timeout = 5000;
  page.setDefaultTimeout(timeout);

  async function waitForSelectors(selectors, frame, options) {
    for (const selector of selectors) {
      try {
        return await waitForSelector(selector, frame, options);
      } catch (err) {
        console.error(err);
      }
    }
    throw new Error('Could not find element for selectors: ' + JSON.stringify(selectors));
  }

  async function scrollIntoViewIfNeeded(element, timeout) {
    await waitForConnected(element, timeout);
    const isInViewport = await element.isIntersectingViewport({ threshold: 0 });
    if (isInViewport) {
      return;
    }
    await element.evaluate(element => {
      element.scrollIntoView({
        block: 'center',
        inline: 'center',
        behavior: 'auto',
      });
    });
    await waitForInViewport(element, timeout);
  }

  async function waitForConnected(element, timeout) {
    await waitForFunction(async () => {
      return await element.getProperty('isConnected');
    }, timeout);
  }

  async function waitForInViewport(element, timeout) {
    await waitForFunction(async () => {
      return await element.isIntersectingViewport({ threshold: 0 });
    }, timeout);
  }

  async function waitForSelector(selector, frame, options) {
    if (!Array.isArray(selector)) {
      selector = [selector];
    }
    if (!selector.length) {
      throw new Error('Empty selector provided to waitForSelector');
    }
    let element = null;
    for (let i = 0; i < selector.length; i++) {
      const part = selector[i];
      if (element) {
        element = await element.waitForSelector(part, options);
      } else {
        element = await frame.waitForSelector(part, options);
      }
      if (!element) {
        throw new Error('Could not find element: ' + selector.join('>>'));
      }
      if (i < selector.length - 1) {
        element = (await element.evaluateHandle(el => el.shadowRoot ? el.shadowRoot : el)).asElement();
      }
    }
    if (!element) {
      throw new Error('Could not find element: ' + selector.join('|'));
    }
    return element;
  }

  async function waitForElement(step, frame, timeout) {
    const count = step.count || 1;
    const operator = step.operator || '>=';
    const comp = {
      '==': (a, b) => a === b,
      '>=': (a, b) => a >= b,
      '<=': (a, b) => a <= b,
    };
    const compFn = comp[operator];
    await waitForFunction(async () => {
      const elements = await querySelectorsAll(step.selectors, frame);
      return compFn(elements.length, count);
    }, timeout);
  }

  async function querySelectorsAll(selectors, frame) {
    for (const selector of selectors) {
      const result = await querySelectorAll(selector, frame);
      if (result.length) {
        return result;
      }
    }
    return [];
  }

  async function querySelectorAll(selector, frame) {
    if (!Array.isArray(selector)) {
      selector = [selector];
    }
    if (!selector.length) {
      throw new Error('Empty selector provided to querySelectorAll');
    }
    let elements = [];
    for (let i = 0; i < selector.length; i++) {
      const part = selector[i];
      if (i === 0) {
        elements = await frame.$$(part);
      } else {
        const tmpElements = elements;
        elements = [];
        for (const el of tmpElements) {
          elements.push(...(await el.$$(part)));
        }
      }
      if (elements.length === 0) {
        return [];
      }
      if (i < selector.length - 1) {
        const tmpElements = [];
        for (const el of elements) {
          const newEl = (await el.evaluateHandle(el => el.shadowRoot ? el.shadowRoot : el)).asElement();
          if (newEl) {
            tmpElements.push(newEl);
          }
        }
        elements = tmpElements;
      }
    }
    return elements;
  }

  async function waitForFunction(fn, timeout) {
    let isActive = true;
    setTimeout(() => {
      isActive = false;
    }, timeout);
    while (isActive) {
      const result = await fn();
      if (result) {
        return;
      }
      await new Promise(resolve => setTimeout(resolve, 100));
    }
    throw new Error('Timed out');
  }
  {
    const targetPage = page;
    await targetPage.setViewport({ "width": 1536, "height": 792 })
  }
  {
    const targetPage = page;
    const promises = [];
    promises.push(targetPage.waitForNavigation());
    await targetPage.goto('https://jenkins.aimsites.nl/spotitube/');
    await Promise.all(promises);
  }
  {
    const targetPage = page;
    const element = await waitForSelectors([["aria/Server URL"], ["#urlInput"]], targetPage, { timeout, visible: true });
    await scrollIntoViewIfNeeded(element, timeout);
    await element.click({ offset: { x: 98, y: 1.23748779296875 } });
  }
  {
    const targetPage = page;
    const element = await waitForSelectors([["aria/Server URL"], ["#urlInput"]], targetPage, { timeout, visible: true });
    await scrollIntoViewIfNeeded(element, timeout);
    const type = await element.evaluate(el => el.type);
    if (["textarea", "select-one", "text", "url", "tel", "search", "password", "number", "email"].includes(type)) {
      await element.type('http://localhost:8010');
    } else {
      await element.focus();
      await element.evaluate((el, value) => {
        el.value = value;
        el.dispatchEvent(new Event('input', { bubbles: true }));
        el.dispatchEvent(new Event('change', { bubbles: true }));
      }, "http://localhost:8010");
    }
  }
  {
    const targetPage = page;
    const element = await waitForSelectors([["aria/User"], ["#mat-input-1"]], targetPage, { timeout, visible: true });
    await scrollIntoViewIfNeeded(element, timeout);
    await element.click({ offset: { x: 60, y: -1.07501220703125 } });
  }
  {
    const targetPage = page;
    const element = await waitForSelectors([["aria/User"], ["#mat-input-1"]], targetPage, { timeout, visible: true });
    await scrollIntoViewIfNeeded(element, timeout);
    const type = await element.evaluate(el => el.type);
    if (["textarea", "select-one", "text", "url", "tel", "search", "password", "number", "email"].includes(type)) {
      await element.type('morty');
    } else {
      await element.focus();
      await element.evaluate((el, value) => {
        el.value = value;
        el.dispatchEvent(new Event('input', { bubbles: true }));
        el.dispatchEvent(new Event('change', { bubbles: true }));
      }, "morty");
    }
  }
  {
    const targetPage = page;
    await targetPage.keyboard.down("Tab");
  }
  {
    const targetPage = page;
    await targetPage.keyboard.up("Tab");
  }
  {
    const targetPage = page;
    const element = await waitForSelectors([["aria/Password"], ["#mat-input-2"]], targetPage, { timeout, visible: true });
    await scrollIntoViewIfNeeded(element, timeout);
    const type = await element.evaluate(el => el.type);
    if (["textarea", "select-one", "text", "url", "tel", "search", "password", "number", "email"].includes(type)) {
      await element.type('mortypassword');
    } else {
      await element.focus();
      await element.evaluate((el, value) => {
        el.value = value;
        el.dispatchEvent(new Event('input', { bubbles: true }));
        el.dispatchEvent(new Event('change', { bubbles: true }));
      }, "mortypassword");
    }
  }
  {
    const targetPage = page;
    await targetPage.keyboard.down("Tab");
  }
  {
    const targetPage = page;
    await targetPage.keyboard.up("Tab");
  }
  {
    const targetPage = page;
    await targetPage.keyboard.down("Enter");
  }
  {
    const targetPage = page;
    await targetPage.keyboard.up("Enter");
  }
  {
    const targetPage = page;
    const element = await waitForSelectors([["body > app-root > app-playlists-overview > div > app-playlists > div > mat-card:nth-child(1) > div:nth-child(4) > button.flex-item.mat-button.ng-star-inserted.cdk-focused.cdk-mouse-focused > span > mat-icon"]], targetPage, { timeout, visible: true });
    await scrollIntoViewIfNeeded(element, timeout);
    await element.click({ offset: { x: 12, y: 10.5 } });
  }
  {
    const targetPage = page;
    const element = await waitForSelectors([["aria/Name"], ["#mat-input-3"]], targetPage, { timeout, visible: true });
    await scrollIntoViewIfNeeded(element, timeout);
    await element.click({ offset: { x: 84, y: 9.25 } });
  }
  {
    const targetPage = page;
    const element = await waitForSelectors([["aria/Name"], ["#mat-input-3"]], targetPage, { timeout, visible: true });
    await scrollIntoViewIfNeeded(element, timeout);
    const type = await element.evaluate(el => el.type);
    if (["textarea", "select-one", "text", "url", "tel", "search", "password", "number", "email"].includes(type)) {
      await element.type('testing edit name');
    } else {
      await element.focus();
      await element.evaluate((el, value) => {
        el.value = value;
        el.dispatchEvent(new Event('input', { bubbles: true }));
        el.dispatchEvent(new Event('change', { bubbles: true }));
      }, "testing edit name");
    }
  }
  {
    const targetPage = page;
    const element = await waitForSelectors([["aria/Ok"], ["#mat-dialog-0 > ng-component > mat-dialog-actions > button.mat-button.mat-primary.cdk-focused.cdk-mouse-focused"]], targetPage, { timeout, visible: true });
    await scrollIntoViewIfNeeded(element, timeout);
    await element.click({ offset: { x: 59, y: 20.25 } });
  }
  {
    const targetPage = page;
    const element = await waitForSelectors([["body > app-root > app-playlists-overview > div > app-playlists > div > mat-card:nth-child(1) > div:nth-child(4)"]], targetPage, { timeout, visible: true });
    await scrollIntoViewIfNeeded(element, timeout);
    await element.click({ offset: { x: 224, y: 42 } });
  }
  {
    const targetPage = page;
    const element = await waitForSelectors([["body > app-root > app-playlists-overview > div > app-playlists > div > mat-card:nth-child(1) > div:nth-child(4) > div"]], targetPage, { timeout, visible: true });
    await scrollIntoViewIfNeeded(element, timeout);
    await element.click({ offset: { x: 172, y: 18 } });
  }
  {
    const targetPage = page;
    const element = await waitForSelectors([["body > app-root > app-playlists-overview > div > app-playlist > div > div > button > span > mat-icon"]], targetPage, { timeout, visible: true });
    await scrollIntoViewIfNeeded(element, timeout);
    await element.click({ offset: { x: 0, y: 19.5 } });
  }
  {
    const targetPage = page;
    const element = await waitForSelectors([["aria/noted"], ["#mat-dialog-1 > ng-component > mat-dialog-content > mat-table > mat-row:nth-child(4) > mat-cell.mat-cell.cdk-column-title.mat-column-title.ng-star-inserted"]], targetPage, { timeout, visible: true });
    await scrollIntoViewIfNeeded(element, timeout);
    await element.click({ offset: { x: 40.75, y: 26.29998779296875 } });
  }
  {
    const targetPage = page;
    const element = await waitForSelectors([["#mat-checkbox-1 > label > div"]], targetPage, { timeout, visible: true });
    await scrollIntoViewIfNeeded(element, timeout);
    await element.click({ offset: { x: 9.75, y: 12.699981689453125 } });
  }
  {
    const targetPage = page;
    const element = await waitForSelectors([["aria/Ok"], ["#mat-dialog-1 > ng-component > mat-dialog-actions > button.mat-button.mat-primary.cdk-focused.cdk-mouse-focused"]], targetPage, { timeout, visible: true });
    await scrollIntoViewIfNeeded(element, timeout);
    await element.click({ offset: { x: 56.26251220703125, y: 11.29998779296875 } });
  }
  {
    const targetPage = page;
    await targetPage.keyboard.down("F12");
  }

  await browser.close();
})();
