# API Test Example
API test example via [JSONPlaceholder](https://jsonplaceholder.typicode.com)

## Test01: GET /posts/1
- verify that userId property exists and equals 1
```json
{
  "userId": 1,
  "id": 1,
  "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
  "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
}
```

## Test02: GET /todos/1
- verify that completed property data type is boolean
```json
{
  "userId": 1,
  "id": 1,
  "title": "delectus aut autem",
  "completed": false
}
```

## Test03: GET /posts/100/comments
- verify that response body size is 5
```json
[
  {
    "postId": 100,
    "id": 496,
    "name": "et occaecati asperiores quas voluptas ipsam nostrum",
    "email": "Zola@lizzie.com",
    "body": "neque unde voluptatem iure\nodio excepturi ipsam ad id\nipsa sed expedita error quam\nvoluptatem tempora necessitatibus suscipit culpa veniam porro iste vel"
  },
  {
    "postId": 100,
    "id": 497,
    "name": "doloribus dolores ut dolores occaecati",
    "email": "Dolly@mandy.co.uk",
    "body": "non dolor consequatur\nlaboriosam ut deserunt autem odit\nlibero dolore non nesciunt qui\naut est consequatur quo dolorem"
  },
  {
    "postId": 100,
    "id": 498,
    "name": "dolores minus aut libero",
    "email": "Davion@eldora.net",
    "body": "aliquam pariatur suscipit fugiat eos sunt\noptio voluptatem eveniet rerum dignissimos\nquia aut beatae\nmodi consequatur qui rerum sint veritatis deserunt est"
  },
  {
    "postId": 100,
    "id": 499,
    "name": "excepturi sunt cum a et rerum quo voluptatibus quia",
    "email": "Wilburn_Labadie@araceli.name",
    "body": "et necessitatibus tempora ipsum quaerat inventore est quasi quidem\nea repudiandae laborum omnis ab reprehenderit ut\nratione sit numquam culpa a rem\natque aut et"
  },
  {
    "postId": 100,
    "id": 500,
    "name": "ex eaque eum natus",
    "email": "Emma@joanny.ca",
    "body": "perspiciatis quis doloremque\nveniam nisi eos velit sed\nid totam inventore voluptatem laborum et eveniet\naut aut aut maxime quia temporibus ut omnis"
  }
]
```

## Test04: GET /posts/100/comments
- verify that body of Davion value equals we expected
```json
[
  {
    "postId": 100,
    "id": 498,
    "name": "dolores minus aut libero",
    "email": "Davion@eldora.net",
    "body": "aliquam pariatur suscipit fugiat eos sunt\noptio voluptatem eveniet rerum dignissimos\nquia aut beatae\nmodi consequatur qui rerum sint veritatis deserunt est"
  }
]
```

## Test05: POST /posts/${postId}/comments
- verify that response data equals data we posted
```json
{
  "userId": 1,
  "id": 1,
  "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
  "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
}
```

## Test06: GET /posts/1
- always fail
