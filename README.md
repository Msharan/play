create user - 

curl --request POST \
  --url http://localhost:9000/users \
  --header 'Content-Type: application/json' \
  --data '{
	"userId": "2",
	"username": "asdad12",
	"password": "abcd",
	"email": "asda.com",
	"status": "ACTIVE"
}'

get user - 
curl --request GET \
  --url http://localhost:9000/users/2

authenticate user - 
curl --request POST \
  --url http://localhost:9000/authn \
  --header 'Content-Type: application/json' \
  --data '{
	"username" : "asdad", 
	"password" : "abcdasda"
}'


create orders - 
curl --request POST \
  --url http://localhost:9000/orders \
  --header 'Content-Type: application/json' \
  --data '{
	"userId": "2",
	"orderId": "3",
	"status": "ACTIVE"
}'

get all orders of user - 
curl --request GET \
  --url http://localhost:9000/users/2/orders

cancel order - 
curl --request DELETE \
  --url http://localhost:9000/users/2/orders/2


