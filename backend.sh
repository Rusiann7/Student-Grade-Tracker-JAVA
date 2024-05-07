#!/bin/bash

declare -r start='1'


echo "Begin?"
echo "1 for yes and 0 for no: "
read -r choice
echo "Initialization Begininng"

sleep 3s

if [ "$choice" -eq  "$start" ]; then

function start1(){
    cd /opt/lampp || exit
    sudo xhost + 
    sudo systemctl start apache2
    sudo ./xampp start
    sudo ./manager-linux-x64.run & ngrok tcp 8181 
    exit 
}
start1

else {
    echo "Error"
}
fi
