package com.github.zukkari.injection

import arrow.core.Try
import arrow.core.fix
import arrow.instances.`try`.monad.monad
import com.sun.tools.attach.VirtualMachine
import java.lang.management.ManagementFactory


object VM {

    fun attach(jarPath: String): Try<Unit> {
        val vmName = ManagementFactory.getRuntimeMXBean().name
        val pid = vmName.substring(vmName.indexOf("@"))

        return Try.monad().binding {
            val vm = VirtualMachine.attach(pid)
            vm.loadAgent(jarPath)
            vm.detach()
        }.fix()
    }

}