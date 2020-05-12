#!/bin/bash

IP=$1
OK=0

for LINE in `cat mappings.txt`; do
  CODE=`curl -o /dev/null -w "%{http_code}" http://$IP:8080$LINE 2>> /dev/null`
  [[ $CODE -ne 200 ]] && echo "Error $CODE en $LINE" && OK=1
done

[[ $OK -eq 0 ]] && echo "Todo bien" || echo "Hubo errores"

