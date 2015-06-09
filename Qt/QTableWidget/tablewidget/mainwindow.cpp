#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QtDebug>
#include <QColor>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    ui->tableWidget->setColumnCount(11);
    ui->tableWidget->setRowCount(NumOfReg);

    /* 设置 tableWidget */
    ui->tableWidget->setHorizontalHeaderLabels(QStringList() << "Id" << "Name" << "Unit" << "Value" << "Min" << "Max"  \
                                               << "Type" << "Mode" << "Enable" << "Last read" << "Last write");
    ui->tableWidget->verticalHeader()->setVisible(false); // 隐藏水平header
    ui->tableWidget->setSelectionBehavior(QAbstractItemView::SelectItems);   // 单个选中
    ui->tableWidget->setSelectionMode(QAbstractItemView::SingleSelection);  // 可以选中单个

    //item->setFlags(Qt::ItemIsSelectable | Qt::ItemIsEditable | Qt::ItemIsUserCheckable | Qt::ItemIsEnabled);

    /* 设置Id Name Unit Min Max Type Mode Last read Last write 字段的值不能修改 */
    /* 设置不可修改的Id Name两列的背景颜色为灰色 */
    for(int i = 0; i < NumOfReg; i++) {
        QTableWidgetItem *item = new QTableWidgetItem();
        item->setBackground(QBrush(QColor(Qt::lightGray)));
        item->setFlags(item->flags() & (~Qt::ItemIsEditable));
        ui->tableWidget->setItem(i, 0, item);
    }
    for(int i = 0; i < NumOfReg; i++) {
        QTableWidgetItem *item = new QTableWidgetItem();
        item->setBackground(QBrush(QColor(Qt::lightGray)));
        item->setFlags(item->flags() & (~Qt::ItemIsEditable));
        ui->tableWidget->setItem(i, 1, item);
    }
    regInfo[0] = new RegisterInfo(0x00, QString("Target motor"), 255, 0, QString("U8"), QString("RW"), false);
    regInfo[1] = new RegisterInfo(0x01, QString("Flags"), U32_MAX, 0, QString("U32"), QString("R"), false);
    regInfo[2] = new RegisterInfo(0x02, QString("Status"), U8_MAX, 0, QString("U8"), QString("R"), false);
    regInfo[3] = new RegisterInfo(0x03, QString("Control mode"), U8_MAX, 0, QString("U8"), QString("RW"), false);
    regInfo[4] = new RegisterInfo(0x04, QString("Speed reference"), 4000, -4000, QString("S32"), QString("R"), false);

    for(int i = 0; i < ui->tableWidget->rowCount(); i++) {
        ui->tableWidget->item(i, 0)->setText(QString::number(regInfo[i]->getId(), 10));
        ui->tableWidget->item(i, 1)->setText(regInfo[i]->getName());
    }
    for(int i = 0; i < ui->tableWidget->rowCount(); i++) {
        QTableWidgetItem *item_max = new QTableWidgetItem();
        item_max->setText(QString::number(regInfo[i]->getMax()));
        item_max->setBackground(QBrush(QColor(Qt::lightGray)));
        item_max->setFlags(item_max->flags() & (~Qt::ItemIsEditable));
        ui->tableWidget->setItem(i, 5, item_max);

        QTableWidgetItem *item_min = new QTableWidgetItem();
        item_min->setText(QString::number(regInfo[i]->getMin()));
        item_min->setBackground(QBrush(QColor(Qt::lightGray)));
        item_min->setFlags(item_min->flags() & (~Qt::ItemIsEditable));
        ui->tableWidget->setItem(i, 4, item_min);

        QTableWidgetItem *item_type = new QTableWidgetItem();
        item_type->setText(regInfo[i]->getType());
        item_type->setBackground(QBrush(QColor(Qt::lightGray)));
        item_type->setFlags(item_type->flags() & (~Qt::ItemIsEditable));
        ui->tableWidget->setItem(i, 6, item_type);

        QTableWidgetItem *item_mode = new QTableWidgetItem();
        item_mode->setText(regInfo[i]->getMode());
        item_mode->setBackground(QBrush(QColor(Qt::lightGray)));
        item_mode->setFlags(item_mode->flags() & (~Qt::ItemIsEditable));
        ui->tableWidget->setItem(i, 7, item_mode);
    }

    for(int i = 0; i < ui->tableWidget->rowCount(); i++) {
        QTableWidgetItem *item_flag = new QTableWidgetItem();
        item_flag->setCheckState(regInfo[i]->getFlag() ? Qt::Checked : Qt::Unchecked);
        ui->tableWidget->setItem(i, 8, item_flag);
    }

    connect(ui->tableWidget->horizontalHeader(), SIGNAL(sectionClicked(int)), this, SLOT(mySortByColumn(int)));
}


void MainWindow::mySortByColumn(int column)
{
    static bool f = true;
    ui->tableWidget->sortByColumn(column, f ? Qt::AscendingOrder : Qt::DescendingOrder);
    f = !f;
}

MainWindow::~MainWindow()
{
    delete ui;
}
// These are gettets and setters of the class RegisterInfo
int RegisterInfo::getId() const
{
    return id;
}
QString RegisterInfo::getName() const
{
    return name;
}
QString RegisterInfo::getUnit() const
{
    return unit;
}
qlonglong RegisterInfo::getValue() const
{
    return value;
}
qlonglong RegisterInfo::getMax() const
{
    return Max;
}
int RegisterInfo::getMin() const
{
    return Min;
}
QString RegisterInfo::getType() const
{
    return type;

}
QString RegisterInfo::getMode() const
{
   return mode;
}
bool RegisterInfo::getFlag() const
{
   return flag;
}
QDate RegisterInfo::getLastRead() const
{
    return lastRead;
}
QDate RegisterInfo::getLastWrite() const
{
    return lastWrite;
}


void RegisterInfo::setId(int id)
{
   this->id = id;
}
void RegisterInfo::setName(QString name)
{
   this->name = name;
}
void RegisterInfo::setUnit(QString unit)
{
    this->unit = unit;
}
void RegisterInfo::setValue(qlonglong value)
{
    this->value = value;
}
void RegisterInfo::setMax(qlonglong Max)
{
    this->Max = Max;
}
void RegisterInfo::setMin(int Min)
{
    this->Min = Min;
}
void RegisterInfo::setType(QString type)
{
    this->type = type;
}
void RegisterInfo::setMode(QString mode)
{
    this->mode = mode;
}
void RegisterInfo::setFlag(bool flag)
{
    this->flag = flag;
}
void RegisterInfo::setLastRead(QDate lastRead)
{
    this->lastRead = lastRead;
}
void RegisterInfo::setLastWrite(QDate lastWrite)
{
    this->lastWrite = lastWrite;
}
