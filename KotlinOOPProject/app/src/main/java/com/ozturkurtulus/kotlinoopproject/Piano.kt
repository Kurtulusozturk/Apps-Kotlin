package com.ozturkurtulus.kotlinoopproject

class Piano:HouseDecor,Instrument {

    var brand :String? = null
    var digital : Boolean? = null

    //Kalıtım aldığımız birinci sınıfın özelliği
    //fun otomatik yazılıyor class da kalıtım  aldığında class name alt enter implement members yap
    override var roomName: String
        get() = "Kitchen"
        set(value) {}
    //Kalıtım aldığımız ikinci sınıfın özelliği
    //fonksiyonun kendisinde tanımlı olduğu için get set işlemleri uygulamadık

}