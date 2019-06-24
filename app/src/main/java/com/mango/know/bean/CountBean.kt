package com.mango.know.bean

class CountBean {
    /**
     * present : {"personals":"0.00","groups":"0.00"}
     * total : {"personal_total":"424.07","group_total":"6174.07"}
     */

    var present: PresentBean? = null
    var total: TotalBean? = null

    class PresentBean {
        /**
         * personals : 0.00
         * groups : 0.00
         */

        var personals: String? = null
        var groups: String? = null
    }

    class TotalBean {
        /**
         * personal_total : 424.07
         * group_total : 6174.07
         */

        var personal_total: String? = null
        var group_total: String? = null
    }
}
