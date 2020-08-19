#!/bin/sh

if apid=$(pgrep -f movierecom.jar)
then
	echo "Stopping java"
	kill $apid
else
    echo "Already stopped"
fi
