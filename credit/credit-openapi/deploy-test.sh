#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../..")"; pwd)
python $tops/script/deployer/main.py -s $tops/script/deployer/settings/test.ini -m credit-openapi
