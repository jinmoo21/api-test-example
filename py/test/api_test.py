import unittest

import requests


class APITest(unittest.TestCase):
    def setUp(self):
        self.url = 'https://jsonplaceholder.typicode.com'

    def tearDown(self):
        pass

    def test_01(self):
        r = requests.get(url=f'{self.url}/posts/1')
        print(r.json())
        self.assertEqual(r.status_code, 200)
        self.assertEqual(r.json()['userId'], 1)

    def test_02(self):
        r = requests.get(url=f'{self.url}/todos/1')
        print(r.json())
        self.assertEqual(r.status_code, 200)
        self.assertEqual(type(r.json()['completed']), bool)

    def test_03(self):
        r = requests.get(url=f'{self.url}/posts/100/comments')
        print(r.json())
        self.assertEqual(r.status_code, 200)
        self.assertEqual(len(r.json()), 5)

    def test_04(self):
        r = requests.get(url=f'{self.url}/posts/100/comments')
        print(r.json())
        self.assertEqual(r.status_code, 200)
        for davion in filter(lambda e: str(e['email']).startswith('Davion'), r.json()):
            # print(davion['body'])
            self.assertEqual(davion['body'], 'aliquam pariatur suscipit fugiat eos sunt\noptio voluptatem eveniet '
                                             'rerum dignissimos\nquia aut beatae\nmodi consequatur qui rerum sint '
                                             'veritatis deserunt est')

    def test_05(self):
        post_id = '100'
        name = '나다'
        email = 'test_id@naver.com'
        body = '본문이에요'

        data = {
            'name': name,
            'email': email,
            'body': body
        }

        r = requests.post(url=f'{self.url}/posts/{post_id}/comments', headers={'Content-Type': 'application/json; '
                                                                                               'charset=UTF-8'},
                          json=data)
        # print(r.json())
        self.assertEqual(r.status_code, 201)
        self.assertEqual(r.json()['postId'], post_id)
        self.assertEqual(r.json()['id'], 501)
        self.assertEqual(r.json()['name'], name)
        self.assertEqual(r.json()['email'], email)
        self.assertEqual(r.json()['body'], body)

    def test_06(self):
        r = requests.get(url=f'{self.url}/posts/1')
        print(r.json())
        self.assertEqual(r.status_code, 404)


if __name__ == '__main__':
    unittest.main()
