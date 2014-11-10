package com.wiredforcode.gradle.spawn
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction

class KillProcessTask extends DefaultTask {

    public static final String LOCK_FILE = '.pid.lock'

    String directory = '.'
    String lockFileName = '.pid.lock'

    @TaskAction
    void kill() {
        def pidFile = new File(directory, lockFileName)
        if(!pidFile.exists()) throw new GradleException("No server running!")

        def pid = pidFile.text
        def cmd = "kill $pid"
        def process = cmd.execute()
        process.waitFor()
        pidFile.delete()
    }

}
