//functions.h
//板载指示灯程序
//板载指示灯例程
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
 #define HUMAN  GPIO_ReadInputDataBit(GPIOC,GPIO_Pin_13)//人体红外输入
 void HumanInfrared_Init(void);
//串口例程
void boardlight_init(int fun);//1 enable red(PA.8);2 enable green(PD.2);3 both enable
void boardlight_fun(int fun,int led);//参数1：指定灯（1/2/3），参数2：指定亮（1）/灭（0）




