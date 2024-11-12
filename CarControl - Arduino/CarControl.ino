#include <SoftwareSerial.h>
#include <mcp2515.h>

SoftwareSerial mySerial(5, 6);
struct can_frame canMsg;
MCP2515 mcp2515(10);

String read;

void setup() {
  Serial.begin(9600);
  mySerial.begin(9600);
  Serial.setTimeout(10);
  mySerial.setTimeout(10);

  mcp2515.reset();
  mcp2515.setBitrate(CAN_95KBPS, MCP_8MHZ);
  mcp2515.setNormalMode();
}

void loop() {

  if(mySerial.available()>0){
    read = mySerial.readString();
    //Serial.println(read);
    int first = read.indexOf(' ');
    int second = read.indexOf(' ',first+1);
    int id = read.substring(0,first).toInt();
    int dlc = read.substring(first+1,second).toInt();

    canMsg.can_id = id;
    canMsg.can_dlc = dlc;

    String str_data = read.substring(second + 1);
    String strs[8];
    int data[dlc];
    int StringCount = 0;

    while (str_data.length() > 0){
      int index = str_data.indexOf(' ');
      if (index == -1){
        strs[StringCount++] = str_data;
        break;
      }
      else{
        strs[StringCount++] = str_data.substring(0, index);
        str_data = str_data.substring(index+1);
      }
    }

    //Serial.println(canMsg.can_id);
    //Serial.println(canMsg.can_dlc);

    for (int i = 0; i < dlc; i++){
      data[i]=strs[i].toInt();
      canMsg.data[i] = data[i];
      //Serial.println(canMsg.data[i]);
    }

    mcp2515.sendMessage(&canMsg);

  }
}