#include "stm32f10x.h"
#include "lcd.h"
#include "delay.h"
#include "key.h"
#include "touch.h"
#include "sys.h"
#include "usart.h"
#include "usart2.h"
#include "usart3.h"
#include "functions.h"
#include "string.h"
#include "usr-c215.h"
#define send 0
int tp=0;
void Load_Drow_Dialog(void)
{
	LCD_Clear(WHITE);//清屏   
 	POINT_COLOR=BLUE;//设置字体为蓝色 
	LCD_ShowString(lcddev.width-32,0,200,16,16,"Rest");//显示rest区域
	LCD_ShowString(0,0,32,16,16,"Send");//显示send区域8*18
  	POINT_COLOR=RED;//设置画笔蓝色 
}
////////////////////////////////////////////////////////////////////////////////
//电容触摸屏专有部分
//画水平线
//x0,y0:坐标
//len:线长度
//color:颜色
void gui_draw_hline(u16 x0,u16 y0,u16 len,u16 color)
{
	if(len==0)return;
	LCD_Fill(x0,y0,x0+len-1,y0,color);	
}
//画实心圆
//x0,y0:坐标
//r:半径
//color:颜色
void gui_fill_circle(u16 x0,u16 y0,u16 r,u16 color)
{											  
	u32 i;
	u32 imax = ((u32)r*707)/1000+1;
	u32 sqmax = (u32)r*(u32)r+(u32)r/2;
	u32 x=r;
	gui_draw_hline(x0-r,y0,2*r,color);
	for (i=1;i<=imax;i++) 
	{
		if ((i*i+x*x)>sqmax)// draw lines from outside  
		{
 			if (x>imax) 
			{
				gui_draw_hline (x0-i+1,y0+x,2*(i-1),color);
				gui_draw_hline (x0-i+1,y0-x,2*(i-1),color);
			}
			x--;
		}
		// draw lines from inside (center)  
		gui_draw_hline(x0-x,y0+i,2*x,color);
		gui_draw_hline(x0-x,y0-i,2*x,color);
	}
}  
//两个数之差的绝对值 
//x1,x2：需取差值的两个数
//返回值：|x1-x2|
u16 my_abs(u16 x1,u16 x2)
{			 
	if(x1>x2)return x1-x2;
	else return x2-x1;
}  
//画一条粗线
//(x1,y1),(x2,y2):线条的起始坐标
//size：线条的粗细程度
//color：线条的颜色
void lcd_draw_bline(u16 x1, u16 y1, u16 x2, u16 y2,u8 size,u16 color)
{
	u16 t; 
	int xerr=0,yerr=0,delta_x,delta_y,distance; 
	int incx,incy,uRow,uCol; 
	if(x1<size|| x2<size||y1<size|| y2<size)return; 
	delta_x=x2-x1; //计算坐标增量 
	delta_y=y2-y1; 
	uRow=x1; 
	uCol=y1; 
	if(delta_x>0)incx=1; //设置单步方向 
	else if(delta_x==0)incx=0;//垂直线 
	else {incx=-1;delta_x=-delta_x;} 
	if(delta_y>0)incy=1; 
	else if(delta_y==0)incy=0;//水平线 
	else{incy=-1;delta_y=-delta_y;} 
	if( delta_x>delta_y)distance=delta_x; //选取基本增量坐标轴 
	else distance=delta_y; 
	for(t=0;t<=distance+1;t++ )//画线输出 
	{  
		gui_fill_circle(uRow,uCol,size,color);//画点 
		xerr+=delta_x ; 
		yerr+=delta_y ; 
		if(xerr>distance) 
		{ 
			xerr-=distance; 
			uRow+=incx; 
		} 
		if(yerr>distance) 
		{ 
			yerr-=distance; 
			uCol+=incy; 
		} 
	}  
}   
////////////////////////////////////////////////////////////////////////////////
//5个触控点的颜色												 
//电阻触摸屏测试函数
char msg[240][30];
char echosun_cul2(char las,int x)
{
	char save=las;
	switch(x)
	{
		case 0:save|=0x80;break;
		case 1:save|=0x40;break;
		case 2:save|=0x20;break;
		case 3:save|=0x10;break;
		case 4:save|=0x08;break;
		case 5:save|=0x04;break;
		case 6:save|=0x02;break;
		case 7:save|=0x01;break;
	}
	return save;	
}
void echosun_cul(int x,int y)
{

		msg[y][x/8]=echosun_cul2(msg[y][x/8],x%8);
}

void rtp_test(void)
{
	u8 key;
	u8 i=0;	  
	while(1)
	{
	
	 	key=KEY_Scan(0);
		tp_dev.scan(0); 		
		#ifndef send
		usrwifi_pointreceive();
		#endif
		if(tp_dev.sta&TP_PRES_DOWN)			//触摸屏被按下
		{	
		 	if(tp_dev.x[0]<lcddev.width&&tp_dev.y[0]<lcddev.height)
			{	
				if(tp_dev.x[0]<32&&tp_dev.y[0]<16){usrwifi_pointsend(&msg);Load_Drow_Dialog();memset(msg,0,sizeof(msg));}//发送
				else if(tp_dev.x[0]>(lcddev.width-32)&&tp_dev.y[0]<16)Load_Drow_Dialog();//清除
				else if(tp_dev.y[0]>240){;}
				else {TP_Draw_Big_Point(tp_dev.x[0],tp_dev.y[0],RED);		//画图
					echosun_cul(tp_dev.x[0],tp_dev.y[0]);
					delay_ms(10);
				}
			}
		}else delay_ms(10);	//没有按键按下的时候 	    
		i++;
		if(i%20==0){tp=!tp;boardlight_fun(1,tp);}
	}
}
const u16 POINT_COLOR_TBL[OTT_MAX_TOUCH]={RED,GREEN,BLUE,BROWN,GRED};  
void hard_init()
{
	boardlight_init(3);
	boardlight_fun(1,1);
}

 int main()
 {	
	 
	 NVIC_Configuration();
	 hard_init();
	 
	 while(1)
	 {
		 
		 
		 
		 
	 }
	 
 //char save[100];

	 //	
 
		//boardlight_fun(1,0);
	 
	/*  
		uart2_init(115200);
	  //uart_init(115200);	 
		 //uart3_init(115200);
	 uart_init(115200);	 	//串口初始化为9600
	 
	 while(1)
	 {
		 boardlight_fun(2,1);
		 delay_ms(500);
		 boardlight_fun(2,0);
		 delay_ms(500);
	 
	 }*/
	//boardlight_fun(1,1);
	/*u8 key;
	
	NVIC_Configuration();
	boardlight_init(3); 
	 uart_init(115200);
	  delay_init();
	LCD_Init();			   	//初始化LCD 	
	KEY_Init();				//按键初始化		 	
	tp_dev.init();			//触摸屏初始化
 	POINT_COLOR=RED;//设置字体为红色 
	
	LCD_ShowString(60,50,200,16,16,"Mini STM32");	
	LCD_ShowString(60,70,200,16,16,"TOUCH TEST");	
	LCD_ShowString(60,90,200,16,16,"ATOM@ALIENTEK");
	LCD_ShowString(60,110,200,16,16,"2014/3/11");
   	if(tp_dev.touchtype!=0XFF)LCD_ShowString(60,130,200,16,16,"Press KEY0 to Adjust");//电阻屏才显示
		while(1){
			tp_dev.scan(0); 
			key=KEY_Scan(0);
			if(tp_dev.sta)break;
			if(key==KEY0_PRES)	//KEY0按下,则执行校准程序
		{
			LCD_Clear(WHITE);//清屏
		    TP_Adjust();  //屏幕校准 
			TP_Save_Adjdata();	 
			Load_Drow_Dialog();
		}
		if(HUMAN==1)
		{
			boardlight_fun(1,1);
		}
		if(HUMAN==0)	
		{
			boardlight_fun(1,0);
		}
		}

	Load_Drow_Dialog();	 	
  rtp_test(); 						//电阻屏测试
	*/

}
