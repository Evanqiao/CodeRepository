#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QDate>
namespace Ui {
class MainWindow;
}
#define U32_MAX 4294967295
#define S32_MAX 2147483647
#define U16_MAX 65535
#define S16_MAX 32767
#define S16_MIN -32768
#define U8_MAX  255

class RegisterInfo
{
public:
    RegisterInfo(int i, QString n, long long Ma, int Mi, QString ty, QString mo, bool fl)
    {
        id = i;
        name = n;
        Max = Ma;
        Min = Mi;
        type = ty;
        mode = mo;
        flag = fl;
    }
    int getId() const;
    QString getName() const;
    QString getUnit() const;
    qlonglong getValue() const;
    qlonglong getMax() const;
    int getMin() const;
    QString getType() const;
    QString getMode() const;
    bool getFlag() const;
    QDate getLastRead() const;
    QDate getLastWrite() const;

    void setId(int);
    void setName(QString);
    void setUnit(QString);
    void setValue(qlonglong);
    void setMax(qlonglong);
    void setMin(int);
    void setType(QString);
    void setMode(QString);
    void setFlag(bool);
    void setLastRead(QDate);
    void setLastWrite(QDate);



private:
    int id;
    QString name;
    QString unit;
    qlonglong value;
    qlonglong Max;
    int Min;
    QString type;
    QString mode;
    bool flag;
    QDate lastRead;
    QDate lastWrite;
};

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();
    static const int NumOfReg = 5;

public slots:
    void mySortByColumn(int column);

private:
    Ui::MainWindow *ui;
    RegisterInfo *regInfo[NumOfReg];
};


#endif // MAINWINDOW_H
