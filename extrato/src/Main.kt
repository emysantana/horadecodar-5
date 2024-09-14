fun main() {
    val nome = solicitarNome()
    var saldo = 1000.0  // seu saldo inicial fictício
    var extrato = mutableListOf<String>()  // uma lista para armazenar o extrato

    println("olá $nome, é um prazer ter você por aqui!")

    do {
        when (mostrarMenu()) {
            1 -> mostrarSaldo(saldo)
            2 -> mostrarExtrato(extrato)
            3 -> saldo = realizarSaque(saldo, extrato)
            4 -> saldo = realizarDeposito(saldo, extrato)
            5 -> saldo = realizarTransferencia(saldo, extrato)
            6 -> println("$nome, foi um prazer ter você por aqui!")
            else -> println("opção inválida. tente novamente.")
        }
    } while (true)
}

fun solicitarNome(): String {
    print("digita seu nome: ")
    return readLine()!!
}

fun mostrarMenu(): Int {
    println("\nmenu:")
    println("1. saldo")
    println("2. extrato")
    println("3. saque")
    println("4. depósito")
    println("5. transferência")
    println("6. sair")
    print("escolha uma opção: ")
    return readLine()!!.toInt()
}

fun validarSenha(): Boolean {
    print("digita a senha: ")
    val senha = readLine()!!.toInt()
    return senha == 3589
}

fun mostrarSaldo(saldo: Double) {
    if (validarSenha()) {
        println("saldo atual: R$ $saldo")
    } else {
        println("senha incorreta. tente novamente.")
    }
}

fun mostrarExtrato(extrato: MutableList<String>) {
    if (validarSenha()) {
        if (extrato.isEmpty()) {
            println("extrato vazio.")
        } else {
            println("extrato:")
            extrato.forEach { println(it) }
        }
    } else {
        println("senha incorreta. tente novamente.")
    }
}

fun realizarSaque(saldo: Double, extrato: MutableList<String>): Double {
    if (validarSenha()) {
        print("digita o valor do saque: ")
        val valor = readLine()!!.toDouble()
        return if (valor > 0 && valor <= saldo) {
            val novoSaldo = saldo - valor
            extrato.add("saque: R$ $valor")
            println("saque realizado com sucesso! novo saldo: R$ $novoSaldo")
            novoSaldo
        } else {
            println("operação não autorizada. veja o valor do saque.")
            saldo
        }
    } else {
        println("senha incorreta. tente novamente.")
        return saldo
    }
}

fun realizarDeposito(saldo: Double, extrato: MutableList<String>): Double {
    if (validarSenha()) {
        print("digita o valor do depósito: ")
        val valor = readLine()!!.toDouble()
        return if (valor > 0) {
            val novoSaldo = saldo + valor
            extrato.add("depósito: R$ $valor")
            println("depósito realizado com sucesso! novo saldo: R$ $novoSaldo")
            novoSaldo
        } else {
            println("operação não autorizada. veja o valor do depósito.")
            saldo
        }
    } else {
        println("senha incorreta. tente novamente.")
        return saldo
    }
}

fun realizarTransferencia(saldo: Double, extrato: MutableList<String>): Double {
    if (validarSenha()) {
        print("digita o número da conta para a transferência: ")
        val conta = readLine()!!
        if (conta.all { it.isDigit() }) {
            print("digita o valor da transferência: ")
            val valor = readLine()!!.toDouble()
            return if (valor > 0 && valor <= saldo) {
                val novoSaldo = saldo - valor
                extrato.add("transferência para conta $conta: R$ $valor")
                println("transferência realizada com sucesso! novo saldo: R$ $novoSaldo")
                novoSaldo
            } else {
                println("operação não autorizada. veja o valor da transferência.")
                saldo
            }
        } else {
            println("número da conta inválido. deve conter apenas números.")
            return saldo
        }
    } else {
        println("senha incorreta. tente novamente.")
        return saldo
    }
}
