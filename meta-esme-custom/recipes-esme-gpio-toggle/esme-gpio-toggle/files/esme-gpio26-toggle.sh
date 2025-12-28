#!/bin/sh

APP="/usr/bin/esme-gpio-toggle"
PIDFILE="/tmp/esme_gpio.pid"

case "$1" in
    start)
        echo "[INFO] Starting $APP"
        $APP &
        echo $! > $PIDFILE
        ;;
    stop)
        echo "[INFO] Stopping $APP"
        if [ -f "$PIDFILE" ]; then
            kill "$(cat $PIDFILE)"
            rm -f $PIDFILE
        fi
        ;;
    status)
        if [ -f "$PIDFILE" ]; then
            echo "[STATUS] $APP is running"
        else
            echo "[STATUS] $APP is not running"
        fi
        ;;
    restart)
        $0 stop
        $0 start
        ;;
    *)
        echo "Usage: $0 {start|stop|status|restart}"
        exit 1
        ;;
esac

exit 0
