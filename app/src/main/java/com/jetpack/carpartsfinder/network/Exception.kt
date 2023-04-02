package com.jetpack.carpartsfinder.network

//One ring to rule them all
open class NetworkException : RuntimeException()

//400 errors
open class BadRequestException : NetworkException()

class ForbiddenException : BadRequestException()

class UnauthorizedException : BadRequestException()

class NotFoundException : BadRequestException()

//500 errors
open class ServerErrorException : NetworkException()

class InternalServerErrorException : ServerErrorException()

class ServiceUnavailableException : ServerErrorException()

class BadGatewayException : ServerErrorException()

class GatewayTimeoutException : ServerErrorException()
