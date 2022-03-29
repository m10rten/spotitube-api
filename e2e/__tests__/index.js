// require express 
const express = require('express');
// make app from express 
const app = express();
// require supertest 
const supertest = require('supertest');
// require dotenv and run it
require('dotenv').config();
// make request from supertest
const request = supertest(process.env.API_URL || 'http://localhost:8010');
// require node-fetch
// const fetch = require('node-fetch');

// write basic test function 
describe('/login', () => {
  it('POST /login', async () => {
    const results = await request.post(`/login`)
      .send({
        user: 'morty',
        password: 'mortypassword',
      })
      .expect(200)
      .then((res) => {
        console.log("res");
      });


    // await fetch(`http://localhost:8010/login`, {
    //   method: 'POST',
    //   headers: {
    //     'Content-Type': 'application/json',
    //   },
    //   body: JSON.stringify({
    //     user: 'morty',
    //     password: 'mortypassword',
    //   }),
    // })

    expect('hello world').toBe('hello world');
  });
});
//# sourceMappingURL=index.js.map