// require supertest 
const supertest = require('supertest');

// require dotenv and run it
require('dotenv').config();

// make request from supertest
const request = supertest(process.env.API_URL || 'http://localhost:8010');

// token to use for playlists: token gets updated in the test: /login correct
let token = '';

describe('/login', () => {
  it('POST /login correct creds', async () => {
    const times = [];
    for(let i = 0; i < 10; i++) {
      const start = Date.now();
      await request.post(`/login`)
        .send({
          user: 'morty',
          password: 'mortypassword',
        })
        .expect(200)
        .then((res) => {
          expect(res.body.token).toBeDefined();
          expect(res.body.user).toBeDefined();
          token = res.body.token;
        });
        const end = Date.now();
        const time = end - start;
        times.push(time);
    } 
    console.table(times);
  });

  it('POST /login wrong creds', async () => {
    const times = [];
    for(let i = 0; i < 10; i++) {
      const start = Date.now();
      await request.post(`/login`)
        .send({
          user: 'user',
          password: 'incorrect',
        })
        .expect(401)
        .then((res) => {
          expect(res.body.token).toBe(undefined);
          expect(res.body.user).toBe(undefined);
        });
      const end = Date.now();
      const time = end - start;
      times.push(time);
    }
    console.table(times);
  });
});

// write a test to test the /playlists endpoint
describe('/playlists', () => {
  it('GET /playlists correct token', async () => {
    const times = [];
    for(let i = 0; i < 10; i++) {
      const start = Date.now();
      await request.get(`/playlists?token=${token}`)
        .expect(200)
        .then((res) => {
          expect(res.body.playlists).toBeDefined();
        });
    const end = Date.now();
    const time = end - start;
    times.push(time);
    }
    console.table(times);
  });

  it('GET /playlists incorrect token', async () => {
    const times = [];
    const start = Date.now();
    for(let i = 0; i < 10; i++) {
      await request.get(`/playlists?token=wrongtoken`)
        .expect(403)
        .then((res) => {
          expect(res.body.playlists).toBe(undefined);
        });
      const end = Date.now();
      const time = end - start;
      times.push(time);
    }
    console.table(times);
  });
});
