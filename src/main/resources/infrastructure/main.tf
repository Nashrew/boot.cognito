provider "aws" {
    # Profile needed here in order to work when running tf plan/apply on windows with aws sso login ¯\_(ツ)_/¯
    profile = ""
    region = "us-east-2"
}

resource "aws_cognito_user_pool" "user_pool" {
    name = "boot-cognito-user-pool" // could add region to this
#     alias_attributes = ["email"] // StackOverlords said to use username_attributes instead of alias; there's no way it's wrong
    auto_verified_attributes = ["email"]
    username_attributes = ["email"]
    admin_create_user_config {
        allow_admin_create_user_only = false
        invite_message_template {
            email_message = "Your username is {username} and temporary password is {####}."
            email_subject = "Your temporary password"
            sms_message = "Your username is {username} and temporary password is {####}."
        }
    }
    schema {
        attribute_data_type = "String"
        developer_only_attribute = false
        mutable = true
        name = "email"
        required = true
        string_attribute_constraints {
            max_length = "2048"
            min_length = "0"
        }
    }
}

resource "aws_cognito_user_pool_client" "user_pool_client" {
    name = "boot-cognito-user-pool-client"
    user_pool_id = aws_cognito_user_pool.user_pool.id
    allowed_oauth_flows = ["code"]
    allowed_oauth_scopes = ["openid"]
    allowed_oauth_flows_user_pool_client = true
    callback_urls = ["http://localhost"] # Placeholder - maybe look into this further
    explicit_auth_flows = ["ALLOW_REFRESH_TOKEN_AUTH", "ALLOW_USER_PASSWORD_AUTH"]
    generate_secret = false
}

resource "aws_cognito_user_group" "user_group" {
    name        = "boot-cognito-user-group"
    user_pool_id = aws_cognito_user_pool.user_pool.id
    description = "Super cool boot cognito user group!"
}

output "user_pool_id" {
    value = aws_cognito_user_pool.user_pool.id
}
