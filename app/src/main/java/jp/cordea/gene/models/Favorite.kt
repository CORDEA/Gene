package jp.cordea.gene.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Yoshihiro Tanaka on 2017/06/03.
 */
@Entity
class Favorite() {

    @Ignore
    constructor(id: String, name: String, symbol: String) : this() {
        this.id = id
        this.name = name
        this.symbol = symbol
    }

    @PrimaryKey
    var id: String = ""

    var name: String = ""

    var symbol: String = ""

}
