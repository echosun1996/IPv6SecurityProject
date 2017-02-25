//functions.c
#include "stm32f10x_gpio.h"
#include "functions.h"



/*
 * 人体红外输入检测 PC_9;
 */

void HumanInfrared_Init()
{
	GPIO_InitTypeDef GPIO_InitStructure;

 	RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOC,ENABLE);//使能PORTA,PORTC时钟

	GPIO_PinRemapConfig(GPIO_Remap_SWJ_JTAGDisable, ENABLE);//关闭jtag，使能SWD，可以用SWD模式调试
	
	GPIO_InitStructure.GPIO_Pin  = GPIO_Pin_13;//PA15
	GPIO_InitStructure.GPIO_Mode = GPIO_Mode_IPU; //设置成上拉输入
 	GPIO_Init(GPIOC, &GPIO_InitStructure);
}

/*
 * 1
 * 1 enable red(PA.8);2 enable green(PD.2);3 both enable
 */
 void boardlight_init(int fun)
 {
	 GPIO_InitTypeDef  GPIO_InitStructure;
	 GPIO_InitStructure.GPIO_Mode = GPIO_Mode_Out_PP; 		 //推挽输出
	 GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;		 //IO口速度为50MHz
	 if(fun==1||fun==3)
	 {
		 RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA, ENABLE);	 //使能PA端口时钟
		 
		 GPIO_InitStructure.GPIO_Pin = GPIO_Pin_8;				 //LED0-->PA.8 端口配置
		 GPIO_Init(GPIOA, &GPIO_InitStructure);					 //根据设定参数初始化GPIOA.8
		 GPIO_SetBits(GPIOA,GPIO_Pin_8);						 //PA.8 输出高

	 }
	 if(fun==2||fun==3)
	 {
	RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOD, ENABLE);	 //使能PD端口时钟
		 
  GPIO_InitStructure.GPIO_Pin = GPIO_Pin_2;	    		 //LED1-->PD.2 端口配置, 推挽输出
  GPIO_Init(GPIOD, &GPIO_InitStructure);	  				 //推挽输出 ，IO口速度为50MHz
		 
  GPIO_SetBits(GPIOD,GPIO_Pin_2); 						 //PD.2 输出高 	
	 }
 }
/*
 * 
 * 参数1：指定灯，参数2：指定亮灭
 */
 void boardlight_fun(int fun,int led)
 {
	 if(fun)
	 {
		 if(led)
		 {
			 GPIO_ResetBits(GPIOA,GPIO_Pin_8); 
		 }
		 else
		 {
			 GPIO_SetBits(GPIOA,GPIO_Pin_8);
		 }
	 }
	 else
		 {
		 if(led)
		 {
			 GPIO_ResetBits(GPIOD,GPIO_Pin_2);
		 }
		 else
		 {
			 GPIO_SetBits(GPIOD,GPIO_Pin_2);
		 } 
	 } 
 }
 
