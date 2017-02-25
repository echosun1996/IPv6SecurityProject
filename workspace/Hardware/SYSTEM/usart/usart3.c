#include "sys.h"
#include "usart3.h"    
#include "stdarg.h"
//////////////////////////////////////////////////////////////////////////////////   
//���ʹ��ucos,����������ͷ�ļ�����.
#if SYSTEM_SUPPORT_OS
#include "includes.h"                   //ucos ʹ��     
#endif
 
//////////////////////////////////////////////////////////////////
//�������´���,֧��printf����,������Ҫѡ��use MicroLIB    

 

__align(8) u8 USART3_TX_BUF[USART3_REC_LEN]; 	//���ͻ���,���USART2_MAX_SEND_LEN�ֽ�
  
#if EN_USART3_RX   //���ʹ���˽���
//����1�жϷ������
//ע��,��ȡUSARTx->SR�ܱ���Ī������Ĵ���      
u8 USART3_RX_BUF[USART3_REC_LEN];     //���ջ���,���USART_REC_LEN���ֽ�.
//����״̬
//bit15��    ������ɱ�־
//bit14��    ���յ�0x0d
//bit13~0��  ���յ�����Ч�ֽ���Ŀ
u16 USART3_RX_STA=0;       //����״̬���    
   
void uart3_init(u32 bound)
{
    //GPIO�˿�����
    GPIO_InitTypeDef GPIO_InitStructure;
    USART_InitTypeDef USART_InitStructure;
    NVIC_InitTypeDef NVIC_InitStructure;
      
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOB, ENABLE);   //ʹ��GPIOAʱ��
    RCC_APB1PeriphClockCmd(RCC_APB1Periph_USART3, ENABLE);  //ʹ��USART3ʱ��
   
    //USART3_TX   GPIOA.9
    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_10;              //PB10
    GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_AF_PP;         //�����������
    GPIO_Init(GPIOB, &GPIO_InitStructure);                  //��ʼ��GPIOA.9
    
    //USART3_RX   GPIOA.10��ʼ��
    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_11;              //PB11
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_IN_FLOATING;   //��������
    GPIO_Init(GPIOB, &GPIO_InitStructure);                  //��ʼ��GPIOA.10  
 
    //Usart1 NVIC ����
    NVIC_InitStructure.NVIC_IRQChannel = USART3_IRQn;
    NVIC_InitStructure.NVIC_IRQChannelPreemptionPriority=3; //��ռ���ȼ�3
    NVIC_InitStructure.NVIC_IRQChannelSubPriority = 3;      //�����ȼ�3
    NVIC_InitStructure.NVIC_IRQChannelCmd = ENABLE;         //IRQͨ��ʹ��
    NVIC_Init(&NVIC_InitStructure);                         //����ָ���Ĳ�����ʼ��VIC�Ĵ���
   
    //USART ��ʼ������
    USART_InitStructure.USART_BaudRate = bound;             //���ڲ�����
    USART_InitStructure.USART_WordLength = USART_WordLength_8b;//�ֳ�Ϊ8λ���ݸ�ʽ
    USART_InitStructure.USART_StopBits = USART_StopBits_1;  //һ��ֹͣλ
    USART_InitStructure.USART_Parity = USART_Parity_No;     //����żУ��λ
    USART_InitStructure.USART_HardwareFlowControl = USART_HardwareFlowControl_None;//��Ӳ������������
    USART_InitStructure.USART_Mode = USART_Mode_Rx | USART_Mode_Tx; //�շ�ģʽ
 
    USART_Init(USART3, &USART_InitStructure);               //��ʼ������1
    USART_ITConfig(USART3, USART_IT_RXNE, ENABLE);          //�������ڽ����ж�
    USART_Cmd(USART3, ENABLE);                              //ʹ�ܴ���1 
}
 
void USART3_IRQHandler(void)                    //����3�жϷ������
{
    u8 Res;
#if SYSTEM_SUPPORT_OS       //���SYSTEM_SUPPORT_OSΪ�棬����Ҫ֧��OS.
    OSIntEnter();    
#endif
    if(USART_GetITStatus(USART3, USART_IT_RXNE) != RESET)  //�����ж�(���յ������ݱ�����0x0d 0x0a��β)
        {
        Res =USART_ReceiveData(USART3); //��ȡ���յ�������
         
        if((USART3_RX_STA&0x8000)==0)//����δ���
            {
            if(USART3_RX_STA&0x4000)//���յ���0x0d
                {
                if(Res!=0x0a)USART3_RX_STA=0;//���մ���,���¿�ʼ
                else USART3_RX_STA|=0x8000;  //��������� 
                }
            else //��û�յ�0X0D
                {   
                if(Res==0x0d)USART3_RX_STA|=0x4000;
						else
                    {
                    USART3_RX_BUF[USART3_RX_STA&0X3FFF]=Res ;
                    USART3_RX_STA++;
                    if(USART3_RX_STA>(USART3_REC_LEN-1))USART3_RX_STA=0;//�������ݴ���,���¿�ʼ����   
                    }        
                }
            }            
     } 
#if SYSTEM_SUPPORT_OS   //���SYSTEM_SUPPORT_OSΪ�棬����Ҫ֧��OS.
    OSIntExit();                                             
#endif
} 

#endif   
	void u3_printf(char* fmt,...)  
{  
	int len,t;
	va_list ap;
	va_start(ap,fmt);
	vsprintf((char*)USART3_TX_BUF,fmt,ap);
	va_end(ap);
			for(t=0;t<len;t++)
			{
				if(USART3_TX_BUF[t]=='\0')break;
				USART3->DR=USART3_TX_BUF[t];
				while((USART3->SR&0X40)==0);//�ȴ����ͽ���
			}
}
 void u3_gets(char *p)
{
	int i=0;
	for(i=0;i<USART3_REC_LEN;i++)
	{
		*(p+i)='\0';
	}
	while(1)
	{
		if(USART3_RX_STA&0x8000)
		{
			int len=USART3_RX_STA&0x3fff,t;
			for(t=0;t<len;t++)
			{
				*(p+t)=USART3_RX_BUF[t];
				while((USART3->SR&0X40)==0);//�ȴ����ͽ���
			}
			USART3_RX_STA=0;
			return;
		}
	}
}
