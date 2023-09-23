#!/bin/bash

INSTANCE_NAME="server"

if ! iptables -t nat -C POSTROUTING -s 10.8.0.0/24 -o eth0 -j MASQUERADE 2>/dev/null; then
    iptables -t nat -A POSTROUTING -s 10.8.0.0/24 -o eth0 -j MASQUERADE
fi

IP=$(curl -s ifconfig.me/all.json | jq -r '.ip_addr') &&\
 sed -i "s/remote my-server-1 1194/remote $IP 1194/" /root/client-configs/base.conf

/usr/sbin/openvpn --daemon ovpn-$INSTANCE_NAME \
    --status /run/openvpn/$INSTANCE_NAME.status 10 \
    --cd /etc/openvpn \
    --script-security 2 \
    --config /etc/openvpn/$INSTANCE_NAME.conf \
    --writepid /run/openvpn/$INSTANCE_NAME.pid

while true; do
    sleep 3600
done
