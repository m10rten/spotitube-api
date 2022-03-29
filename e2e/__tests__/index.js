// require supertest 
const supertest = require('supertest');

// require dotenv and run it
require('dotenv').config();

// make request from supertest
const request = supertest(process.env.API_URL || 'http://localhost:8010');

let token = '';
// write basic test function 
describe('/login', () => {
  it('POST /login correct creds', async () => {
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
  });
  it('POST /login wrong creds', async () => {
    await request.post(`/login`)
      .send({
        user: 'morty',
        password: 'userFalsePassword',
      })
      .expect(401)
      .then((res) => {
        expect(res.body.token).toBe(undefined);
        expect(res.body.user).toBe(undefined);
      });
  });
});

// write a test to test the /playlists endpoint
describe('/playlists', () => {
  it('GET /playlists correct token', async () => {
    console.log(token);
    await request.get(`/playlists?token=${token}`)
      .expect(200)
      .then((res) => {
        console.log(res.body.playlists);
        expect(res.body.playlists).toBeDefined();
      });
  });
  // write a test to test the GET /playlists endpoint with an incorrect token
  it('GET /playlists incorrect token', async () => {
    await request.get(`/playlists?token=wrongtoken`)
      .expect(403)
      .then((res) => {
        expect(res.body.playlists).toBe(undefined);
      });
  });
});
