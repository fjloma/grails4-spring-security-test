Test setup

```shell
$ sdk use java 8.0.312-zulu
$ sdk use grails 4.0.13
$ sdk use groovy 2.5.14

$ grails --version
| Grails Version: 4.0.13
| JVM Version: 1.8.0_312
```

Follow tutorial https://grails.github.io/grails-spring-security-core/4.0.x/index.html#tutorials
until step 26.1.8.8

Change the SecureController class to print the user roles.

`SecureController.groovy`

```groovy
package com.mycompany.myapp

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder

class SecureController {
   @Secured('ROLE_ADMIN')
   def index() {
      render 'Secure access only '+ SecurityContextHolder.getContext().getAuthentication().getAuthorities()
   }
}
```

### Test fail
Add the following line to application.groovy a simple role hierarchy. **Note there is no spaces arround the ">" symbol.**

```groovy 
grails.plugin.springsecurity.roleHierarchy = '''
        ROLE_ADMIN>ROLE_USER
'''
```

Run the application (grails run-app) and go to http://localhost:8080/secure and log with me / password. It shows:
```plain
Secure access only [ROLE_ADMIN]
```
when te expected value should be

```plain
Secure access only [ROLE_USER, ROLE_ADMIN]
```

### Test with spaces
Change the hierarchy configuration on application.groovy to have spaces arround the ">"
```groovy 
grails.plugin.springsecurity.roleHierarchy = '''
        ROLE_ADMIN > ROLE_USER
'''
```
Run the application (grails run-app) and go to http://localhost:8080/secure and log with me / password. Now it shows the correct values:

```plain
Secure access only [ROLE_USER, ROLE_ADMIN]
```
