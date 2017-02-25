//functions.c
#include "stm32f10x_gpio.h"
#include "functions.h"



/*
 * ������������� PC_9;
 */

void HumanInfrared_Init()
{
	GPIO_InitTypeDef GPIO_InitStructure;

 	RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOC,ENABLE);//ʹ��PORTA,PORTCʱ��

	GPIO_PinRemapConfig(GPIO_Remap_SWJ_JTAGDisable, ENABLE);//�ر�jtag��ʹ��SWD��������SWDģʽ����
	
	GPIO_InitStructure.GPIO_Pin  = GPIO_Pin_13;//PA15
	GPIO_InitStructure.GPIO_Mode = GPIO_Mode_IPU; //���ó���������
 	GPIO_Init(GPIOC, &GPIO_InitStructure);
}

/*
 * 1
 * 1 enable red(PA.8);2 enable green(PD.2);3 both enable
 */
 void boardlight_init(int fun)
 {
	 GPIO_InitTypeDef  GPIO_InitStructure;
	 GPIO_InitStructure.GPIO_Mode = GPIO_Mode_Out_PP; 		 //�������
	 GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;		 //IO���ٶ�Ϊ50MHz
	 if(fun==1||fun==3)
	 {
		 RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA, ENABLE);	 //ʹ��PA�˿�ʱ��
		 
		 GPIO_InitStructure.GPIO_Pin = GPIO_Pin_8;				 //LED0-->PA.8 �˿�����
		 GPIO_Init(GPIOA, &GPIO_InitStructure);					 //�����趨������ʼ��GPIOA.8
		 GPIO_SetBits(GPIOA,GPIO_Pin_8);						 //PA.8 �����

	 }
	 if(fun==2||fun==3)
	 {
	RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOD, ENABLE);	 //ʹ��PD�˿�ʱ��
		 
  GPIO_InitStructure.GPIO_Pin = GPIO_Pin_2;	    		 //LED1-->PD.2 �˿�����, �������
  GPIO_Init(GPIOD, &GPIO_InitStructure);	  				 //������� ��IO���ٶ�Ϊ50MHz
		 
  GPIO_SetBits(GPIOD,GPIO_Pin_2); 						 //PD.2 ����� 	
	 }
 }
/*
 * 
 * ����1��ָ���ƣ�����2��ָ������
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
 
