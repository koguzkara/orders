To run 
```bash
docker-compose up
```


POST localhost:9899/product/create
```json
{
  "name":"1",
  "description":"1 description",
  "prices":[
    { 
		"money":
			{
				"amount":7.0,
				"currency":"USD"
			}
		},
		{
		"money":
			{
				"amount":13.0,
				"currency":"EUR"
			}
	}	
  ]
}
```

POST localhost:9899/product/2/update
```json
{
  "id":2,
  "name":"p1",
  "description":"p1 description updated u"
}
```
POST localhost:9899/product/1/price/add
```json
{
	"amount":139.0,
	"currency":"YTL"
}
```


GET localhost:9899/products

POST localhost:9899/order/create
```json
{
	"email":"kkara@com.is",
	"orderDate":"2021-12-15 17:20:00",
	"products":[
		{
			"productPriceId":2,
			"size":1
		},
		{
			"productPriceId":3,
			"size":2
		}]
}
```

GET localhost:9899/order/4/sum


GET localhost:9899/order/sumquery?startDate=2021-08-01 00:00:00&endDate=2022-12-01 00:00:00

GET localhost:9899/order/detailquery?startDate=2021-08-01 00:00:00&endDate=2022-12-01 00:00:00

GET localhost:9899/order/4/detail

GET localhost:9899/orders

