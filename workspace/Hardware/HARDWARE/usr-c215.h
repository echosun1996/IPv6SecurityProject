#ifndef __usr_c215
#define __usr_c215		
#define debug
#include "stm32f10x.h"
void usrwifi_connect(void);
int usrwifi_init(void);
int usrwifi_cmd(u8 *cmd);
int usrwifi_pointsend(char (*p)[][30]);
#endif
void usrwifi_receiveinit(void);
int usrwifi_pointreceive(void);
