**mycodgen是codgen代码生成工具的一个在线示例项目，在不同的示例配置和模板中分别展示了codgen的大部分功能及其使用技巧，希望通过这个项目实践，用户可以对codgen有更深入的了解。**

1、两大生成功能：*应用框架*和*应用模块*代码生成；对应两套模板——应用框架模板（template/FRAMEWORK）和应用模块模板（template/SAMPLE），注：模板仅为演示用途，并不保证其可用性。
2、三个codgen配置文件：codgen-FRAMEWORK是应用框架配置文件；codgen-SAMPLE1是单应用独立配置文件示例；codgen-SAMPLE2是具有继承关系的多应用配置文件示例。
3、代码输出路径：windows系统为“d:/data/codgen/[author]/[project]”，其他操作系统为“/data/codgen/[author]/[project]”；其中“[author]”为创建人个人文件夹，“[project]”为项目文件夹；生成代码操作前请先清理输出文件夹。
4、开发工具：Intellij IDEA 14.1.4 + gradle2.5，jdk1.6或以上版本