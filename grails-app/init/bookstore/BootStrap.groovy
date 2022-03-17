package bookstore

import grails.gorm.transactions.Transactional
import com.mycompany.myapp.User
import com.mycompany.myapp.UserRole
import com.mycompany.myapp.Role


class BootStrap {
    def init = {
        addTestUser()
    }

    @Transactional
    void addTestUser() {
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()

        def testUser = new User(username: 'me', password: 'password').save()

        UserRole.create testUser, adminRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 1
        assert Role.count() == 1
        assert UserRole.count() == 1
    }
}