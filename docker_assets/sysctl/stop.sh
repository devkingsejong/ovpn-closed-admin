#!/bin/bash

INSTANCE_NAME="server"

PID=$(pgrep -f "openvpn --daemon ovpn-$INSTANCE_NAME")

if [ -z "$PID" ]; then
    echo "OpenVPN instance $INSTANCE_NAME is not running."
else
    kill $PID
    echo "OpenVPN instance $INSTANCE_NAME has been stopped."
fi
