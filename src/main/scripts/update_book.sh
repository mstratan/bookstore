curl -s -X POST -d '{"id": "1", "title": "How to grow oranges 2.0", "author": "Mr. Orangeton", "price": 70.1}' \
-H "Content-Type: application/json" "localhost:8080/book/1"