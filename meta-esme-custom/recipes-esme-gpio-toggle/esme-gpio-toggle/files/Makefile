CC ?= gcc
CFLAGS += -Wall -Wextra
TARGET = esme-gpio-toggle

all: $(TARGET)

$(TARGET): main.c
	$(CC) $(CFLAGS) -o $(TARGET) main.c

install:
	mkdir -p $(INSTALL_DIR)/usr/bin
	cp $(TARGET) $(INSTALL_DIR)/usr/bin/

clean:
	rm -f $(TARGET)
