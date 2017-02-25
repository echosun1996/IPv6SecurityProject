//usr-c15有人wifi模块
//模块连接串口2，需要用串口1调试，可关闭
#include "stm32f10x.h"
#include "usart2.h"
#include "delay.h"
#include "usr-c215.h"
#include "string.h"
#ifdef debug
#include "usart.h"
#endif	 
void usrwifi_connect()
{
	char save[100];
	 while(1)
	 {
		while((USART1->SR&0X40)==0);
		u1_printf("connect\r\n");
		while((USART2->SR&0X40)==0);
		USART2->DR='+'; 
		delay_ms(50);
		while((USART2->SR&0X40)==0);	
		USART2->DR='+';
		delay_ms(50);
		while((USART2->SR&0X40)==0);
		USART2->DR='+';      
		delay_ms(100);				
		while((USART2->SR&0X40)==0);	
		USART2->DR='a';   
		delay_ms(500); 
		while((USART2->SR&0X40)==0);
		USART2->DR='A';      
		delay_ms(20);		
		while((USART2->SR&0X40)==0);
		USART2->DR='T';      
		delay_ms(20);			
		while((USART2->SR&0X40)==0);
		USART2->DR='\r';      
		delay_ms(20);						 
		 while((USART2->SR&0X40)==0);
		USART2->DR='\n';      
		USART2_RX_STA=0;
		u2_gets(save);
		u1_printf("%s",save);
		if(save[0]!='\0'){return;}
		delay_ms(200);
	 }
}


int usrwifi_init()
{
	char receive[200];
	
	usrwifi_connect();
	
	
	u2_printf("AT+RELD\r\n");
	u2_gets(receive);
	u1_printf("%s",receive);
	u2_gets(receive);
	u1_printf("%s",receive);
	
	usrwifi_connect();
	delay_ms(2000);
	
	//u2_printf("AT+E=on\r\n");
	//u2_gets(receive);
	//u1_printf("%s",receive);
	//delay_ms(20);

	u2_printf("AT+WMODE=STA\r\n");//设置连接模式
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
		delay_ms(20);
	
	u2_printf("AT+WSSSID=CPS\r\n");//设置SSID
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
	delay_ms(20);
	
	u2_printf("AT+WSKEY=WPA2PSK,AES,CCECICT5F\r\n");//设置验证信息
	u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
	delay_ms(20);
	
	u2_printf("AT+ASWD=SSS\r\n");//设置/查询模块搜索口令
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
		delay_ms(20);
		
			u2_printf("AT+SEARCH=13140\r\n");//设置/查询模块搜索端口
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
		delay_ms(20);
		
	
		u2_printf("AT+Z\r\n\r\n");//重启
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
		delay_ms(20);
		
		usrwifi_connect();
delay_ms(1864);

		u2_printf("AT+NETP=UDP,SERVER,9999,10.30.19.158\r\n");//设置运行模式
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
	delay_ms(20);

	
	u2_printf("AT+Z\r\n");//重启
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
	delay_ms(20);
	
	
	usrwifi_connect();
	delay_ms(1864);
	delay_ms(1864);
	
	
		u2_printf("AT+NETP\r\n");//显示网络协议
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
	delay_ms(20);
	
	
			u2_printf("AT+WANN\r\n");//显示连接信息
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
	delay_ms(20);
	
	u2_printf("AT+ENTM\r\n");//开启透传
	u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
	delay_ms(100);
	return 1;
}

int usrwifi_pointsend(char (*p)[][30])
{
	int i=0;
	//int test=1;
	for(i=0;i<240;i+=4)
	{
		//u2_printf("%d\n",test++);
	u2_printf("%s%s%s%s\n",(*p)[i],(*p)[i+1],(*p)[i+2],(*p)[i+3]);
	delay_ms(30);
	}
	return 1;
}
#include"touch.h"
#include"lcd.h"
void usrwifi_receiveinit()
{
	char receive[200];
	
	usrwifi_connect();
	
	/*
	u2_printf("AT+RELD\r\n");
	u2_gets(receive);
	u1_printf("%s",receive);
	u2_gets(receive);
	u1_printf("%s",receive);
	
	usrwifi_connect();
	delay_ms(2000);
	*/

	u2_printf("AT+WMODE=STA\r\n");//设置连接模式
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
		delay_ms(20);
	
	u2_printf("AT+WSSSID=CPS\r\n");//设置SSID
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
	delay_ms(20);
	
	u2_printf("AT+WSKEY=WPA2PSK,AES,CCECICT5F\r\n");//设置验证信息
	u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
	delay_ms(20);
	
	u2_printf("AT+ASWD=SSS\r\n");//设置/查询模块搜索口令
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
		delay_ms(20);
		
			u2_printf("AT+SEARCH=13140\r\n");//设置/查询模块搜索端口
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
		delay_ms(20);
		
	
		u2_printf("AT+Z\r\n\r\n");//重启
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
		delay_ms(20);
		
		usrwifi_connect();
	//delay_ms(6000);
	

		u2_printf("AT+NETP=UDP,SERVER,9999,10.30.19.162\r\n");//设置运行模式
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
	delay_ms(20);

	
	u2_printf("AT+Z\r\n");//重启
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
	delay_ms(20);
	
	
	usrwifi_connect();
	delay_ms(1864);
	delay_ms(1864);
	
		u2_printf("AT+NETP\r\n");//显示网络协议
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
	delay_ms(20);
	
	
			u2_printf("AT+WANN\r\n");//显示连接信息
		u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
	delay_ms(20);
	
	u2_printf("AT+ENTM\r\n");//开启透传
	u2_gets(receive);
	u1_printf("%s",receive);
		u2_gets(receive);
	u1_printf("%s",receive);
	delay_ms(100);
}
void cul_receive2(int k,int i,char *p)
{
	int hang=k*4+i/30;
	int lie=i%30;
	if(((*p)&0x80)==0x80)
	{
		TP_Draw_Big_Point(hang,lie,RED);
	}
	if(((*p)&0x40)==0x40)
	{
		TP_Draw_Big_Point(hang,lie+1,RED);
	}
	if(((*p)&0x20)==0x20)
	{
		TP_Draw_Big_Point(hang,lie+2,RED);
	}
	if(((*p)&0x10)==0x10)
	{
		TP_Draw_Big_Point(hang,lie+3,RED);
	}
	if(((*p)&0x08)==0x08)
	{
		TP_Draw_Big_Point(hang,lie+4,RED);
	}
	if(((*p)&0x04)==0x04)
	{
		TP_Draw_Big_Point(hang,lie+5,RED);
	}
	if(((*p)&0x02)==0x02)
	{
		TP_Draw_Big_Point(hang,lie+6,RED);
	}
	if(((*p)&0x01)==0x01)
	{
		TP_Draw_Big_Point(hang,lie+7,RED);
	}
}
void cul_receive(int k,char *p)
{
	int i=0;
	for(i=0;i<120;i++)//cul 120
	cul_receive2(k,i,p+i);
}
int usrwifi_pointreceive()
{
	
	char receive[200];
	int i=0;
	memset(receive,0,sizeof(receive));
	
	for(i=0;i<60;i++)
	{
		u2_gets(receive);
		cul_receive(i,receive);//4*30=120
		delay_ms(30);
	}
	return 1;
}
