### 1. To run 
```bash
./mvnw clean clean compile jib:dockerBuild
docker-compose up
```

### 2. General Explanation
 Product has only name and description. But Product might have multiple ProductPrice with different currencies or in 
 different times. Order references to ProductPrice, so it will always keep the prices of  product. Basically prices of 
 products do not change a new price is defines everytime. And isActive field can be used to present only valid prices.

### 3. Missing Part
 - Java Docs are considerably missing
 - Api documentation with openapi(swagger) is missing
 - Unit and integration testing is missing

### 4. Authentication 
 I will use JWT to authenticate any api calls with specific refresh time

### 5. Redundancy 
 Use of container orchestrations tools,like kubernetes and docker swarm, with  a loadbalancer like Ingress should provide
redundancy as long as application is stateless.

### 6. Examples
 OpenApi should generate much more better documentation for API, but below should be enough for quick spin.
POST localhost:8080/product/create
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

POST localhost:8080/product/{productid}/update
```json
{
  "id":2,
  "name":"p1",
  "description":"p1 description updated u"
}
```
POST localhost:8080/product/{productid}/price/add
```json
{
	"amount":139.0,
	"currency":"YTL"
}
```


GET localhost:8080/products

POST localhost:8080/order/create
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

GET localhost:8080/order/{orderId}/sum


GET localhost:8080/order/sumquery?startDate=2021-08-01 00:00:00&endDate=2022-12-01 00:00:00
format for query parameters: yyyy-MM-dd HH:mm:ss

GET localhost:8080/order/detailquery?startDate=2021-08-01 00:00:00&endDate=2022-12-01 00:00:00
format for query parameters: yyyy-MM-dd HH:mm:ss

GET localhost:8080/order/{orderId}/detail

GET localhost:8080/orders

