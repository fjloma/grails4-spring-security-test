package com.mycompany.myapp

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder

class SecureController {

    @Secured('ROLE_ADMIN')
    def index() {
        render 'Secure access only '+ SecurityContextHolder.getContext().getAuthentication().getAuthorities()
    }
}
