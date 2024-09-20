package com.momid

interface Data<T> {
    fun clone(): T
}

fun <T: Data<T>> breadth(data: T, paths: List<(T) -> Boolean>): List<T> {
    val finals = ArrayList<T>()
    val currents = ArrayList<T>()
    currents.add(data)
    val nexts = ArrayList<T>()

    while (true) {
        nexts.clear()
        currents.forEach {
            nexts.addAll(breadthALayer(finals, it, paths))
        }
        if (nexts.isEmpty()) {
            break
        } else {
            currents.clear()
            currents.addAll(nexts)
        }
    }

    return finals
}

fun <T: Data<T>> breadthALayer(finals: ArrayList<T>, data: T, paths: List<(T) -> Boolean>): List<T> {
        val datas = ArrayList<T>()
        paths.forEach {
            val data = data.clone()
            val applicable = it(data)
            if (applicable) {
                datas.add(data)
            }
        }

        if (datas.isEmpty()) {
            finals.add(data)
            return listOf()
        }

        return datas
}
