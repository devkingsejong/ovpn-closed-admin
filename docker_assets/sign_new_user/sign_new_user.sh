#!/bin/bash

userName="$1"

cd /root/EasyRSA-3.0.8
expect ./01_gen-req.exp $userName

cp pki/private/$userName.key ~/client-configs/keys

expect ./02_sign-req.exp $userName
cp pki/issued/$userName.crt ~/client-configs/keys/
