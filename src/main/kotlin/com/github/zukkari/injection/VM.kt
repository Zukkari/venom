package com.github.zukkari.injection

import arrow.core.Try
import arrow.core.fix
import com.sun.tools.attach.VirtualMachine
import java.lang.management.ManagementFactory


object VM {

    fun attach(jarPath: String): Try<Unit> {
        val vmName = ManagementFactory.getRuntimeMXBean().name
        val pid = vmName.substring(0, vmName.indexOf("@"))

        return Try {
            val vm = VirtualMachine.attach(pid)
            vm.loadAgent(jarPath)
            vm.detach()
        }.fix()
    }

}