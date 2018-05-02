import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.System.`in`

val reader = BufferedReader(InputStreamReader(`in`))

fun main(args: Array<String>) {
    var data = mutableMapOf<String, String>()
    do {

        val command = reader.readLine().toLowerCase().split(" ")
        when (command[0]) {
            "help" -> {
                println("exit - âûéòè\n" +
                        "help - ïîêàçàòü ñïðàâêó \n" +
                        "add <key> <value>\n" +
                        "remove key=<key>|value=<value>\n" +
                        "find key=<key>|value=<value>")
            }
            "add" -> {
                val key = command[1]
                val value = command[2]

                data.put(key, value)
                println("Óñïåøíî äîáàâëåíà ïàðà $key: $value")
            }
            "remove" -> {
                if (command[1].split("=")[0] == "key") {
                    val key = command[1].split("=")[1]
                    if (key in data) {
                        val value = data[key]
                        data.remove(key)
                        println("Óñïåøíî óäàëåíà ïàðà $key: $value")
                    } else {
                        println("Ïàð ñ òàêèì êëþ÷îì íå ñóùåñòâóåò.")
                    }
                } else if (command[1].split("=")[0] == "value") {
                    val value = command[1].split("=")[1] 
                    if (value in data.values) {
                        for (key in data.keys) {
                            if (data[key] == value) {
                                data.remove(key)

                                println("Óñïåøíî óäàëåíà ïàðà $key: $value")
                            }
                        }
                    } else {
                        println("Ïàð ñ òàêèì çíà÷åíèåì íå ñóùåñòâóåò.")
                    }
                }

            }
            "find" -> {
                var isFound = false
                if (command[1].split("=")[0] == "key") {
                    val key = command[1].split("=")[1]
                    for (foundKey in data.keys) {
                        val value = data[foundKey]
                        if (key in foundKey) {
                            println("Íàéäåíà ïàðà: $foundKey: $value")
                            isFound = true
                        }
                    }
                } else if (command[1].split("=")[0] == "value") {
                    val value = command[1].split("=")[1]
                    for (i in data.keys) {
                        var foundValue = data[i]
                        if (foundValue != null && value in foundValue) {
                            println("Íàéäåíà ïàðà: $i: $foundValue")
                            isFound = true
                        }
                    }
                }
                if (!isFound) {
                    print("Íè÷åãî íå íàéäåíî")
                }
            }
            else -> {
                println("Íåèçâåñòíàÿ êîìàíäà. Èñïîëüçóéòå êîìàíäó help äëÿ ïîëó÷åíèÿ ñïðàâêè.")
            }
        }
    } while (command[0] != "exit")
}
