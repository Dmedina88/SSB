package <%= appPackage %>.data.repository

import <%= appPackage %>.data.repository.api.Api

class Repository(api: Api) : Api by api