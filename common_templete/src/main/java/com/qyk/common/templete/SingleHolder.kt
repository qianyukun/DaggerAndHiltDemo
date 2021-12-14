package com.qyk.common.templete

/**
 * 创建单例的帮助类，可以常见任意带参数的单例
 *
 *  class SingletonTest private constructor(context: Context) {
 *
 *    companion object {
 *
 *        fun getInstance(context: Context): SingletonTest {
 *
 *            return SingleHolder(::SingletonTest).getInstance(context)
 *
 *        }
 *
 *    }
 *
 * }
 */
class SingleHolder<out T : Any, in A>(create: (A) -> T) {
    private var creator: ((A) -> T)? = create

    @Volatile
    private var instance: T? = null

    /**
     * 获取单例入口
     */
    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }
        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                return i2
            }
            instance = creator!!(arg)
            creator = null
            return@synchronized instance!!
        }
    }
}