// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'security.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'security.UserRole'
grails.plugin.springsecurity.authority.className = 'security.Role'


grails.plugin.springsecurity.rest.token.validation.enableAnonymousAccess = true


grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/', access: ['permitAll']],
        [pattern: '/error', access: ['permitAll']],
        [pattern: '/index', access: ['permitAll']],
        [pattern: '/index.gsp', access: ['permitAll']],
        [pattern: '/shutdown', access: ['permitAll']],
        [pattern: '/assets/**', access: ['permitAll']],
        [pattern: '/**/js/**', access: ['permitAll']],
        [pattern: '/**/css/**', access: ['permitAll']],
        [pattern: '/**/images/**', access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/assets/**', filters: 'none'],
        [pattern: '/**/js/**', filters: 'none'],
        [pattern: '/**/css/**', filters: 'none'],
        [pattern: '/**/images/**', filters: 'none'],
        [pattern: '/**/favicon.ico', filters: 'none'],
        // the index page.
        [pattern: '/', filters: 'none'
        ],

        // stateless chain
        [
                pattern: '/**',
                filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
        ],

//        [pattern: '/oauth/**', filters: 'JOINED_FILTERS'],

]

grails.plugin.springsecurity.rest.token.storage.jwt.secret = "I need to put a really good secret here, coming from an Environment variable" // + System.currentTimeMillis()
grails.plugin.springsecurity.rest.token.storage.jwt.expiration = 15 // 60 second expiration.

