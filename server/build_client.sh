#!/bin/bash
cd ../client
npm install
rm -rf ../server/src/main/resources/static
ng build --prod --output-path=../server/src/main/resources/static
cd ../server