### Sales Taxes Service

#### Assumptions
- Java 8 installed and on the path
- Maven installed and on the path
- The Service will run on the 8080 port

#### Building the Service
* mvn clean package

#### Running the Service
From controllers/target:
java -jar sales-taxes.jar

#### Running the Service on a Specific Port
java -jar sales-taxes.jar --server.port=1234

#### Testing the Service
From your preferred HTTP client send a POST Request with a shopping basket payload to the following end point: taxes/calculate

* Example Request Json:
```json
{
	"items": [{
		"name": "Bottle of Perfume",
		"price": 27.99,
		"quantity": 1.00,
		"taxExemptionType": "TAXABLE",
		"saleType": "IMPORTED"
	}, {
		"name": "Bottle of Perfume",
		"price": 18.99,
		"quantity": 1.00,
		"taxExemptionType": "TAXABLE",
		"saleType": "DOMESTIC"
	}, {
		"name": "Packet of Headache Pills",
		"price": 9.75,
		"quantity": 1.00,
		"taxExemptionType": "EXEMPT",
		"saleType": "DOMESTIC"
	}, {
		"name": "Box of Chocolate",
		"price": 11.25,
		"quantity": 1.00,
		"taxExemptionType": "EXEMPT",
		"saleType": "IMPORTED"
	}]
}
```

* Example Response Json:
```json
{
	"shoppingBasket": {
		"items": [{
			"name": "Bottle of Perfume",
			"price": 32.19,
			"quantity": 1.00,
			"taxExemptionType": "TAXABLE",
			"saleType": "IMPORTED"
		}, {
			"name": "Bottle of Perfume",
			"price": 20.89,
			"quantity": 1.00,
			"taxExemptionType": "TAXABLE",
			"saleType": "DOMESTIC"
		}, {
			"name": "Packet of Headache Pills",
			"price": 9.75,
			"quantity": 1.00,
			"taxExemptionType": "EXEMPT",
			"saleType": "DOMESTIC"
		}, {
			"name": "Box of Chocolate",
			"price": 11.85,
			"quantity": 1.00,
			"taxExemptionType": "EXEMPT",
			"saleType": "IMPORTED"
		}]
	},
	"salesTaxes": 6.70,
	"total": 74.68
}
```
