//functions.h
//����ָʾ�Ƴ���
//����ָʾ������
/*
boardlight_init(1);
while(1)
{
	boardlight_fun(1,0);
	Delay(3000000);
	boardlight_fun(1,1);
	Delay(3000000);
}
*/
 #define HUMAN  GPIO_ReadInputDataBit(GPIOC,GPIO_Pin_13)//�����������
 void HumanInfrared_Init(void);
//��������
void boardlight_init(int fun);//1 enable red(PA.8);2 enable green(PD.2);3 both enable
void boardlight_fun(int fun,int led);//����1��ָ���ƣ�1/2/3��������2��ָ������1��/��0��




