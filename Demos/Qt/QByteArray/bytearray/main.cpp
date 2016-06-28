#include <QCoreApplication>
#include <QByteArray>
#include <QDebug>
int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);
    QByteArray bArray;
    bArray.resize(5);
    unsigned char ch[5];

    bArray[0] = 2;
    bArray[1] = 3;
    bArray[2] = 0x0A;
    bArray[3] = 5;
    bArray[4] = 0xFF;

    for(int i = 0; i < 5; i++) {
        ch[i] = bArray[i];

    }

    //bArray = bArray.toHex();
    for(int i = 0; i < 5; i++) {
        qDebug() << (unsigned char) bArray[i];
    }



    return a.exec();
}
