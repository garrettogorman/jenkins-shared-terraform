#!/usr/bin/groovy

// def call(String lockId, String environment, String blueprintDir, String githubTeam) {
  def call(String lockId, String environment, String blueprintDir) {
    def terraform = new com.garrettogorman.jenkins.Terraform()

    lock(lockId) {
      dir(blueprintDir) {

        terraform.setup(environment)

        // switch(terraform.plan(environment)) {
        //   case 0:
        //     echo 'No changes to make'
        //     terraform.apply(environment)   
        //     break
        //   case 1:
        //     err 'An error has occured'
        //     break
        //   case 2:
        //     timeout(time: 1, unit: 'DAYS') {
        //       input message: "Apply infrastructure changes? (Click 'Proceed' to continue)"
        //       echo 'Applying changes'
        //       terraform.apply(environment)
        //     }
        //     break
        // }

        terraform.destroy(environment)

        return terraform.output()
      }
    }
}