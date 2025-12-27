#include <gpiod.h>
#include <stdio.h>
#include <unistd.h>

#define GPIO_CHIP "/dev/gpiochip0"
#define GPIO_LINE 26

int main(void)
{
    struct gpiod_chip *chip;
    struct gpiod_line *line;

    chip = gpiod_chip_open(GPIO_CHIP);
    if (!chip) {
        perror("gpiod_chip_open");
        return 1;
    }

    line = gpiod_chip_get_line(chip, GPIO_LINE);
    if (!line) {
        perror("gpiod_chip_get_line");
        gpiod_chip_close(chip);
        return 1;
    }

    if (gpiod_line_request_output(line, "esme-gpio-toggle", 0) < 0) {
        perror("gpiod_line_request_output");
        gpiod_chip_close(chip);
        return 1;
    }

    while (1) {
        gpiod_line_set_value(line, 1);
        sleep(1);
        gpiod_line_set_value(line, 0);
        sleep(1);
    }

    gpiod_chip_close(chip);
    return 0;
}
