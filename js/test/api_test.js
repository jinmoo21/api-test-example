const request = require('supertest');
const assert = require('assert');

describe('GET /posts/1', () => {
    it('verify that userId property exists and equals 1', (done) => {
        request('https://jsonplaceholder.typicode.com')
        .get('/posts/1')
        .expect(200)
        .expect(res => {
            var json = res.body;
            // console.log(JSON.stringify(json));
            assert(json.hasOwnProperty('userId'));
            assert(json.userId === 1);
        })
        .end((err, res) => {
            if (err) return done(err);
            return done();
        });
    });
});

describe('GET /todos/1', () => {
    it('verify that completed property data type is boolean', (done) => {
        request('https://jsonplaceholder.typicode.com')
        .get('/todos/1')
        .expect(200)
        .expect((res) => {
            var json = res.body;
            // console.log(JSON.stringify(json));
            assert(json.hasOwnProperty('completed'));
            assert(typeof json.completed === 'boolean');
        })
        .end((err, res) => {
            if (err) return done(err);
            return done();
        });
    });
});

describe('GET /posts/100/comments', () => {
    it('verify that response body size is 5', (done) => {
        request('https://jsonplaceholder.typicode.com')
        .get('/posts/100/comments')
        .expect(200)
        .expect((res) => {
            var json = res.body;
            // console.log(JSON.stringify(json));
            assert(json.length === 5);
        })
        .end((err, res) => {
            if (err) return done(err);
            return done();
        });
    });

    it('verify that body of Davion value equals we expected', (done) => {
        request('https://jsonplaceholder.typicode.com')
        .get('/posts/100/comments')
        .expect(200)
        .expect((res) => {
            var davionBody = res.body.filter(p => p.email.startsWith("Davion"))[0].body;
            // console.log(JSON.stringify(davionBody));
            assert(davionBody === 'aliquam pariatur suscipit fugiat eos sunt\noptio voluptatem eveniet rerum dignissimos\nquia aut beatae\nmodi consequatur qui rerum sint veritatis deserunt est');
        })
        .end((err, res) => {
            if (err) return done(err);
            return done();
        });
    });
});

var postId = '100';
var name = '나다';
var email = 'test_id@naver.com';
var body = '본문이에요';

describe('POST /posts/'+ postId + '/comments', () => {
    it('verify that response data equals data we posted', (done) => {
        request('https://jsonplaceholder.typicode.com')
        .post('/posts/'+ postId + '/comments')
        .set('Content-Type', 'application/json; charset=UTF-8')
        .send(
            {
                name: name, 
                email: email, 
                body: body
            }
        )
        .expect(201)
        .expect((res) => {
            var json = res.body;
            // console.log(JSON.stringify(json));
            assert(json.hasOwnProperty('postId'));
            assert(json.postId === postId);
            assert(json.hasOwnProperty('id'));
            assert(json.id === 501);
            assert(json.hasOwnProperty('name'));
            assert(json.name === name);
            assert(json.hasOwnProperty('email'));
            assert(json.email === email);
            assert(json.hasOwnProperty('body'));
            assert(json.body === body);
        })
        .end((err, res) => {
            if (err) return done(err);
            return done();
        });
    });
});

describe('GET /posts/1', () => {
    it('always fail', (done) => {
        request('https://jsonplaceholder.typicode.com')
        .get('/posts/1')
        .expect(404, done)
    });
});