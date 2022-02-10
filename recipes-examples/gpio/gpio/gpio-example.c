#include "gpio-example.h"
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <math.h>
#include "gpio_lib.h"

int msleep (int millisec);

int main(void)
{
    char ret = 0;
    ret = sunxi_gpio_init();
    //printf("Hello, world!\r\n");
    if(ret){
        printf("sunxi_gpio_init ERROR\n");
        exit(-1);
    };
    sunxi_gpio_set_cfgpin(SUNXI_GPA(0), SUNXI_GPIO_OUTPUT);
    while (1)
    {
        //printf("BLINK OFF\r\n");
        sunxi_gpio_output(SUNXI_GPA(0), 0);
        msleep(200);
        //printf("BLINK ON\r\n");
        sunxi_gpio_output(SUNXI_GPA(0), 1);
        msleep(300);
    }
    return 0;
}

int msleep (int millisec)
{
  useconds_t usec;
  int ret;

  usec = (useconds_t) millisec *1000;
  ret = usleep (usec);
  return (ret);
}