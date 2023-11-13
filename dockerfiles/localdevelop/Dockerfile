FROM amazoncorretto:17

ARG USERNAME=vscode
ARG USER_UID=1000
ARG USER_GID=$USER_UID

# Create the user
RUN yum install -y shadow-utils tar
RUN groupadd --gid $USER_GID $USERNAME \
    && useradd --uid $USER_UID --gid $USER_GID -m $USERNAME \
    #
    # [Optional] Add sudo support. Omit if you don't need to install software after connecting.
    && yum update -y \
    && yum install -y sudo \
    && echo $USERNAME ALL=\(root\) NOPASSWD:ALL > /etc/sudoers.d/$USERNAME \
    && chmod 0440 /etc/sudoers.d/$USERNAME

# ********************************************************
# * Anything else you want to do like clean up goes here *
# ********************************************************
# Add IaC Tools
RUN yum install -y git
# RUN yum install -y yum-utils \
#     && yum-config-manager --add-repo https://rpm.releases.hashicorp.com/AmazonLinux/hashicorp.repo \
#     && yum -y install terraform

# RUN yum install -y python3 \
#     && pip3 install awscli --upgrade

# [Optional] Set the default user. Omit if you want to keep the default as root.
USER $USERNAME