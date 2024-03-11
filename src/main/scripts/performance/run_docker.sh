cd $(dirname $0)
docker run -i --network host loadimpact/k6 run - <C:/Users/mstratan/GP/bookstore/src/main/scripts/performance/script.js